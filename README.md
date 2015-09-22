# Phonebook exercise

Create a simple HTTP service to represent a phone book.

Acceptance criteria.
- List all entries in the phone book.
- Create a new entry to the phone book.
- Remove an existing entry in the phone book.
- Update an existing entry in the phone book.
- Search for entries in the phone book by surname.

A phone book entry must contain the following details:
- Surname
- Firstname
- Phone number
- Address (optional)

## NOTES

The service returns JSON representation of the phone book entries. 

### Simple Implementation

For the sake of simplicity, I've tried to avoid loading heavy duty frameworks such as Spring, or ORM. 

### Persistent Storage
The choice of persistent storage is interesting. Given that we have a simple data model, and we don't expect to require to perform complex relational queries, I've opted for an in memory Key-Value store using MultiMaps. Since this is a constraint that could potentially hurt the development of the project in the future, I've decided to plug the store at the DAO level, using an interface that abstract querying logic and infrastructure from the API. 

I have gone for the simplest solution, but moving forward, if we decided to keep the key-value store, there are plenty of high capacity and high availability commercial and open source solutions. Also it would be easy to add indexing to other values. 

Alternatively, we could use a relational database and JDBC.  

### Address 
Modeling addresses using a simple model. Different countries have different representations for addresses. This has not been taken care for the sake of simplicity. Also an user may have more than one address, and our model doesn't contemplate this. 

### Phone Numbers 