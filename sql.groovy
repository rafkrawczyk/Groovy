@GrabConfig(systemClassLoader = true)
@Grab('com.h2database:h2:2.2.224')

import groovy.sql.Sql

// Connection Settings (Using H2 in-memory database)
def dbUrl = 'jdbc:h2:mem:groovyDb;DB_CLOSE_DELAY=-1'
def dbUser = 'sa'
def dbPassword = ''
def dbDriver = 'org.h2.Driver'

def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)

println "--- Database Connected ---"

// Create a Table
sql.execute('''
    CREATE TABLE IF NOT EXISTS Employee (
        id INT PRIMARY KEY,
        first_name VARCHAR(50),
        last_name VARCHAR(50),
        role VARCHAR(50),
        salary DECIMAL(10,2)
    )
''')
println "--- Table Created ---"

sql.execute('DELETE FROM Employee')

def employees = [
    [101, 'Alice', 'Smith', 'Developer', 85000.50],
    [102, 'Bob', 'Jones', 'Manager', 95000.00],
    [103, 'Charlie', 'Brown', 'Designer', 78000.25],
    [104, 'Diana', 'Prince', 'Director', 120000.00]
]

employees.each { emp ->
    sql.execute("INSERT INTO Employee (id, first_name, last_name, role, salary) VALUES (?, ?, ?, ?, ?)", emp)
}
println "--- Data Inserted into ${employees.size()} rows ---"

def csvFile = new File("export.csv")

csvFile.withWriter { writer ->

    writer.writeLine("ID,First Name,Last Name,Role,Salary")

    sql.eachRow("SELECT * FROM Employee") { row ->

        def line = "${row.id},${row.first_name},${row.last_name},${row.role},${row.salary}"

        writer.writeLine(line)

        println "Exporting: $row.first_name $row.last_name"
    }
}

sql.close()

println "\n--- Success! ---"
println "Data exported to: ${csvFile.absolutePath}"