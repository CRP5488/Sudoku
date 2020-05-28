import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SudokuSolver {
    private Sudoku start;
    private boolean debug;
    private Sudoku solved;

    public SudokuSolver(Sudoku start){
        this.start = start;
        this.debug = false;
    }

    public SudokuSolver(Sudoku start, boolean debug){
        this.start = start;
        this.debug = debug;
    }

    public Optional<Sudoku> Solve(){
        return this.Solve(start);
    }

    public Optional<Sudoku> Solve(Sudoku current){
        if(current.isGoal()){
            this.solved = current;
            return Optional.of(current);
        }
        else {
            for(Sudoku successor : current.getSuccessors()){
                if(debug){
                    successor.printBoard();
                }

                if(successor.isValid()){
                    if(debug){
                        System.out.println("Valid!");
                    }

                    Optional<Sudoku> possibleSolution = Solve(successor);
                    if(possibleSolution.isPresent()){
                        return possibleSolution;
                    }
                }
                else {
                    if(debug){
                        System.out.println("Invalid!");
                    }

                }
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args){
        Map<Integer, Integer> start = new HashMap<>();
        start.put(5, 9);
        start.put(7, 7);
        start.put(9, 7);
        start.put(11, 6);
        start.put(14, 1);
        start.put(21, 4);
        start.put(24, 5);
        start.put(32, 6);
        start.put(33, 2);
        start.put(36, 6);
        start.put(38, 7);
        start.put(44, 4);
        start.put(45, 5);
        start.put(49, 2);
        start.put(53, 3);
        start.put(55, 4);
        start.put(57, 3);
        start.put(58, 5);
        start.put(60, 1);
        start.put(65, 9);
        start.put(78, 8);
        System.out.println("Start: ");
        Sudoku startBoard = new Sudoku(start);
        startBoard.printBoard();
        SudokuSolver solver = new SudokuSolver(startBoard, false);
        Optional<Sudoku> solvedBoard = solver.Solve();
        if(solvedBoard.isPresent()){
            System.out.println("Solved: ");
            solvedBoard.get().printBoard();
        }
        else{
            System.out.println("No solution");
        }
    }


}
