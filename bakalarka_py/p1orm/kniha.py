from sqlalchemy import String, Float
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column

class Base(DeclarativeBase):
    pass


class Kniha(Base):
    __tablename__ = "kniha"

    nazov: Mapped[str] = mapped_column(String, primary_key=True)
    cena: Mapped[float] = mapped_column(Float, default=0.0)

    def __repr__(self):
        return f"Kniha {{nazov={self.nazov}, cena={self.cena}}}"