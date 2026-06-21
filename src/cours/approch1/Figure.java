package cours.approch1;

public abstract class Figure {
    protected final TypeFigure type;
    
    public TypeFigure getType() {
        return type;
    }
    public Figure(TypeFigure type) {
        this.type = type;
    }
    public abstract double surface();
    
}
