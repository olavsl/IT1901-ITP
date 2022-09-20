package app;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class User {

    private String username;
    private String password;
    private List<Transaction> transactions = new ArrayList<>();


    public User(String username, String password, String confirmedPassword) {
        checkIfUserExists(username);
        setUsername(username);
        setPassword(password, confirmedPassword);; // For later: Hash passord for more security
    }

    public String getUsername() {
        return this.username;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    private void setUsername(String username) {
        if (username.length() < 4 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 4 and 20 characters!");
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username must only contain letters and numbers!");
        }
        
        if(!checkIfUserExists(username)){
            this.username = username;
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/app/users.txt",true));
                bw.newLine();
                bw.write(username);
                bw.close();
            } catch (Exception ex) {
                return;
            }
        }else{
            throw new IllegalArgumentException("Username is already taken!");
        }
        

    }
    private void setPassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new IllegalArgumentException("Passwords does not match!");
        }

        this.password = password;
    }

    private boolean checkIfUserExists(String username) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/app/users.txt"));
            String s;
            List<String> users = new ArrayList<String>();
            while((s = br.readLine()) != null){
                users.add(s);

            }
            br.close();
            if(users.contains(username)){
                return true;

            }else{
                return false;
            }
        }catch(Exception ex){
            throw new IllegalArgumentException("File to read not found");
        }
    }
}
