import groovy.xml.MarkupBuilder

def booksData = [
        [isbn: "978-1935182443", title: "Groovy in Action 2nd Edition", author: "Dierk Koenig", price: 50.58],
        [isbn: "978-1935182948", title: "Making Java Groovy", author: "Ken Kousen", price: 33.96],
        [isbn: "978-1937785307", title: "Programming Groovy 2: Dynamic Productivity for the Java Developer", author: "Venkat Subramaniam", price: 28.92]
]

def writer = new StringWriter()
def xml = new MarkupBuilder(writer)

xml.books {
    booksData.each { b ->
        book(isbn: b.isbn) {
            title(b.title)
            author(b.author)
            price(b.price)
        }
    }
}

println writer.toString()