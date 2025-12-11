import groovy.xml.MarkupBuilder

def booksData = [
        [isbn: "978-1935182443", title: "Groovy in Action 2nd Edition", author: "Dierk Koenig", price: 50.58],
        [isbn: "978-1935182948", title: "Making Java Groovy", author: "Ken Kousen", price: 33.96],
        [isbn: "978-1937785307", title: "Programming Groovy 2: Dynamic Productivity for the Java Developer", author: "Venkat Subramaniam", price: 28.92]
]

def writer = new StringWriter()
def html = new MarkupBuilder(writer)

html.books {
    head {
        title("Groovy Book List")
        style("table { border-collapse: collapse; width: 100%; } th, td { border: 1px solid #ddd; padding: 8px; test-align: left; } th { background-color: #f2f2f2; }")
    }
    body {
        h1("My Groovy Library")

        table {
            thead {
                tr {
                    th("ISBN")
                    th("Title")
                    th("Author")
                    th("Price")
                }
            }
            tbody {
                booksData.each { book ->
                    tr {
                        td(book.isbn)
                        td(book.title)
                        td(book.author)
                        td(book.price)
                    }
                }
            }
        }
    }
}

println writer.toString()