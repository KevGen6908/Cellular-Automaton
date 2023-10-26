import java.util.ArrayList;

public class Cell {
    public CellStatus status;
    private ArrayList<Cell> near; // ячейки вокруг
    public Cell(){
        status = CellStatus.None;
        near = new ArrayList<>();
    }

    private CellStatus getStatus(){
        return status;
    }

    public void nearAdd(Cell cell){
        near.add(cell);
    }
    public void preparatoryStep1(){
        int around = countNearCells();
        status = status.step1(around);
    }

    public void preparatoryStep2(){
        status = status.step2();
    }
    private  int countNearCells(){
        int count  = 0;
        for(Cell cell : near){
            if(cell.status.isLive()){
                count ++;
            }
        }
        return count;
    }

    public void turn() {
        for (Cell cell : near) {
            cell.status = cell.status.isLive() ? CellStatus.None : CellStatus.Live;
        }
    }
}

