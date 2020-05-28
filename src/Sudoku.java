import java.awt.event.MouseAdapter;
import java.security.PublicKey;
import java.util.*;

public class Sudoku {

    private List<Integer> board;
    private Integer lastSuccessorsIndex;

    public Sudoku(Map<Integer, Integer> intitial){
        this.board = new LinkedList<>();
        this.lastSuccessorsIndex = 0;
        for(Integer i = 0; i < 81; i++){
            if(intitial.containsKey(i)){
                this.board.add(intitial.get(i));
            }
            else {
                this.board.add(0);
            }
        }
    }

    private Sudoku(Sudoku other, Integer index, Integer value){
        this.lastSuccessorsIndex = index;
        this.board = new LinkedList<>(other.board);
        this.board.set(index, value);
        //System.out.println(this.board.size());
    }

    public Collection<Sudoku> getSuccessors(){
        Integer index = 0;
        while (index < 81){
            if(this.board.get(index).equals(0)){
                break;
            }
            index++;
        }
        if(index.equals(81)){
            return null;
        }
        Set<Sudoku> sucessors = new HashSet<>();
        for(Integer i = 1; i <= 9; i++){
            sucessors.add(new Sudoku(this, index, i));
        }
        this.lastSuccessorsIndex = index;
        return sucessors;
    }

    public boolean isValid(){
        Integer index = this.lastSuccessorsIndex;
        return (!this.rowContaints(Math.floorDiv(index, 9), this.board.get(index)) &&
                !this.columnContains(index % 9, this.board.get(index))
                && !this.boxContains(getBoxIndex(index), this.board.get(index)));
    }

    public boolean isGoal(){
        for(Integer number : this.board){
            if(number.equals(0)){
                return false;
            }
        }
        return true;
    }

    private boolean rowContaints(Integer rowIndex, Integer number){
        Integer index = rowIndex*9;
        Integer endOfRow = index + 8;
        int instancesOfNumber = 0;
        while(index <= endOfRow){
            if(this.board.get(index).equals(number)){
                instancesOfNumber += 1;
            }
            index++;
        }
        return instancesOfNumber > 1;
    }

    private boolean columnContains(Integer columnIndex, Integer number){
        Integer index = columnIndex;
        int instancesOfNumber = 0;
        while(index < 81){
            if(this.board.get(index).equals(number)){
                instancesOfNumber += 1;
            }
            index += 9;
        }
        return instancesOfNumber > 1;
    }

    private boolean boxContains(Integer boxIndex, Integer number){
        Set<Integer> indexes = getIndexesInBox(boxIndex);
        int instancesOfNumber = 0;
        for(Integer index : indexes) {
            if(this.board.get(index).equals(number)){
                instancesOfNumber += 1;
            }
        }
        return instancesOfNumber > 1;
    }

    private static Integer getBoxIndex(Integer index){
        if(Math.floorDiv(index, 27) == 0){
            if(index % 9 <= 2){
                return 0;
            }
            else if(index % 9 <= 5){
                return 1;
            }
            else {
                return 2;
            }
        }
        else if(Math.floorDiv(index, 27) == 1){
            if(index % 9 <= 2){
                return 3;
            }
            else if(index % 9 <= 5){
                return 4;
            }
            else {
                return 5;
            }
        }
        else{
            if(index % 9 <= 2){
                return 6;
            }
            else if(index % 9 <= 5){
                return 7;
            }
            else {
                return 8;
            }
        }
    }

    private static Set<Integer> getIndexesInBox(Integer boxIndex){
        Set<Integer> indexes = new HashSet<>();
        switch (boxIndex){
            case 0: indexes = new HashSet<>(Arrays.asList(0, 1, 2, 9, 10, 11, 18, 19, 20));
                break;
            //System.out.println(" Not Default");
            case 1: indexes = new HashSet<>(Arrays.asList(3, 4, 5, 12, 13, 14, 21, 22, 23));
                break;
            //System.out.println(" Not Default");
            case 2: indexes = new HashSet<>(Arrays.asList(6, 7, 8, 15, 16, 17, 24, 25, 26));
                break;
            //System.out.println(" Not Default");
            case 3: indexes = new HashSet<>(Arrays.asList(27, 28, 29, 36, 37, 38, 45, 46, 47));
                break;
            //System.out.println(" Not Default");
            case 4: indexes = new HashSet<>(Arrays.asList(30, 31, 32, 39, 40, 41, 48, 49, 50));
                break;
            case 5: indexes = new HashSet<>(Arrays.asList(33, 34, 35, 42, 43, 44, 51, 52, 53));
                break;
            //System.out.println(" Not Default");
            case 6: indexes = new HashSet<>(Arrays.asList(54, 55, 56, 63, 64, 65, 72, 73, 74));
                break;
            case 7: indexes = new HashSet<>(Arrays.asList(57, 58, 59, 66, 67, 68, 75, 76, 77));
                break;
            //System.out.println(" Not Default");
            case 8: indexes = new HashSet<>(Arrays.asList(60, 61, 62, 69, 70, 71, 78, 79, 80));
                break;
            //System.out.println("Default");
        }
        return indexes;
    }

    public void printBoard(){
        System.out.println("==========================================================");
        for (int row = 0; row < 9; row++){
            this.printRow(row);
            if(row % 3 == 2){
                System.out.println("==========================================================");
            }
            else {
                System.out.println("----------------------------------------------------------");
            }
        }
    }

    private void printRow(int rowIndex){
        Integer startIndex = rowIndex * 9;
        String line = "|| ";
        for(int i = 0; i < 9; i ++){
            line = line + this.board.get(startIndex + i) + "  |";
            if(i % 3 == 2){
                line = line + "|";
            }
            line = line + "  ";
        }
        System.out.println(line);
    }

    public static void main(String[] args){
//        Map<Integer, Integer> f = new HashMap<>();
//        for(Integer i = 0; i <81; i++){
//            f.put(i, i % 9);
//        }
//        Sudoku s = new Sudoku(f);
//        s.printBoard();
        for(Integer i = 0; i < 9; i++){
            Set<Integer> n = getIndexesInBox(i);
            for(Integer f : n){
                System.out.print(" " + f + " ");
            }
            System.out.print("\n");
        }
    }

}
