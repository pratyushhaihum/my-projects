class User:
    def __init__(self, username, password):
        self.username = username
        self.password = password

class UserManager:
    def __init__(self):
        self.users = {}

    def register(self, username, password):
        if username in self.users:
            print("Username already exists.")
            return False
        self.users[username] = User(username, password)
        print("User registered successfully.")
        return True

    def login(self, username, password):
        if username in self.users and self.users[username].password == password:
            print("Login successful.")
            return True
        print("Invalid username or password.")
        return False

class Book:
    def __init__(self, title, author, price, stock):
        self.title = title
        self.author = author
        self.price = price
        self.stock = stock

class BookStore:
    def __init__(self):
        self.books = []

    def add_book(self, title, author, price, stock):
        new_book = Book(title, author, price, stock)
        self.books.append(new_book)
        print("Book added successfully.")

    def display_books(self):
        if not self.books:
            print("No books available.")
            return
        for book in self.books:
            print("Title: {book.title}, Author: {book.author}, Price: {book.price}, Stock: {book.stock}")

    def search_book(self, title):
        for book in self.books:
            if book.title.lower() == title.lower():
                print("Found: Title: {book.title}, Author: {book.author}, Price: {book.price}, Stock: {book.stock}")
                return book
        print("Book not found.")
        return None

    def purchase_book(self, title):
        book = self.search_book(title)
        if book and book.stock > 0:
            book.stock -= 1
            print("Book purchased successfully.")
        elif book:
            print("Book out of stock.")


def main():
    user_manager = UserManager()
    bookstore = BookStore()

    while True:
        print("\n1. Register\n2. Login\n3. Add Book\n4. Display Books\n5. Search Book\n6. Purchase Book\n7. Exit")
        choice = input("Enter your choice: ")

        if choice == 1:
            username = input("Enter username: ")
            password = input("Enter password: ")
            user_manager.register(username, password)
        elif choice == 2:
            username = input("Enter username: ")
            password = input("Enter password: ")
            if user_manager.login(username, password):
                print("Welcome!")
        elif choice == 3:
            title = input("Enter book title: ")
            author = input("Enter book author: ")
            price = float(input("Enter book price: "))
            stock = int(input("Enter book stock: "))
            bookstore.add_book(title, author, price, stock)
        elif choice == 4:
            bookstore.display_books()
        elif choice == 5:
            title = input("Enter book title to search: ")
            bookstore.search_book(title)
        elif choice == 6:
            title = input("Enter book title to purchase: ")
            bookstore.purchase_book(title)
        elif choice == 7:
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
