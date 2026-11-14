from sqlalchemy import create_engine
from sqlalchemy.orm import Session
from Person import Base, Person

engine = create_engine("sqlite:///person.db", echo=False)
Base.metadata.create_all(engine)

def verzia1():
    with Session(engine, expire_on_commit=False) as session:
        p = Person(name="Fero")
        session.add(p)
        session.commit()
        print("id:", p.id)
        p2 = session.get(Person, p.id)
        print("p is p2:    ", p is p2)
        print("p == p2:    ", p == p2)

def verzia2():
    with Session(engine, expire_on_commit=False) as session:
        p = Person(name="Fero")
        session.add(p)
        session.commit()
        id = p.id
        print("id:", id)
    with Session(engine) as session:
        p2 = session.get(Person, id)
        print("p is p2:    ", p is p2)
        print("p == p2:    ", p == p2)

if __name__ == "__main__":
    #verzia1()
    verzia2()
