public enum CellStatus {
    None,
    Born,
    Live,
    Died;

    public CellStatus step1(int around ){
        switch (this){
            case None: return (around == 2) ? Born : None;
            case Live: return (around <= 1 || around >= 4) ? Died : Live;
            default: return this;
        }
    }

    public CellStatus step2(){
        switch (this){
            case Born: return Live;
            case Died: return None;
            default: return this;
        }
    }

    public boolean isLive(){
        return this == Live || this ==Died;
    }
}
