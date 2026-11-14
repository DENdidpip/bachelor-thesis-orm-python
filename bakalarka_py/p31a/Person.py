from datetime import date
from sqlalchemy import String
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column

class Base(DeclarativeBase):
    pass

class Person(Base):
    __tablename__ = "person"

    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str | None] = mapped_column(String(100))
    salary: Mapped[float] = mapped_column(default=0.0)
    born: Mapped[date | None]
    married: Mapped[bool] = mapped_column(default=False)
