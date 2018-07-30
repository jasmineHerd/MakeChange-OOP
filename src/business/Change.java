/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author jasmineherd
 */
public class Change {

    private int qoh, doh, noh, poh, q, d, n, p;//on hand

    private String errmsg, actionmsg;
    private double total;

    public Change(int qoh, int doh, int noh, int poh) {
        this.qoh = qoh;
        this.doh = doh;
        this.noh = noh;
        this.poh = poh;

        this.errmsg = "";
        this.actionmsg = "";
    }

    public double getChangeTotal() {

        this.total = (qoh * .25) + (doh * .1) + (noh * .05) + (poh * .01);
        return this.total;
    }

    public String getErrorMsg() {
        return this.errmsg;
    }

    public String getQGiven() {
        return String.valueOf(this.q);

    }

    public String getDGiven() {
        return String.valueOf(this.d);

    }

    public String getNGiven() {
        return String.valueOf(this.n);

    }

    public String getPGiven() {
        return String.valueOf(this.p);

    }

    public void setChange(int cents) {
        this.errmsg="";
        int r;
        r = cents;

        //if(r > getChangeTotal()){ this.errmsg = "Invalid change value: not between 1 and 100"}
        if (r > (getChangeTotal() * 100)) {
            this.errmsg = "Change needed is greater than change on hand";
        } else if (r < 1 || r > 100) {
            this.errmsg = "Invalid change value: not between 1 and 100";
        } else {
            
                this.q = r / 25;

                if (this.q > this.qoh) {
                    this.q = this.qoh;
                }
                r = r - (this.q * 25);

                this.d = r / 10;
                if (this.d > this.doh) {
                    this.d = this.doh;
                }
                r = r - this.d * 10;

                this.n = r / 5;
                if (this.n > this.noh) {
                    this.n = this.noh;
                }
                r = r - (this.n * 5);

                this.p = r;
                if (this.p > this.poh) {
                    this.p = this.poh;
                }
                r -= this.p;

                if (r != 0) {
                    this.errmsg = "Perfect change cannot be made: " + r + 
                            " cents short";
                }
            }
        }

    

    public void setSave() {
        this.errmsg = "";
        try {
          
            PrintWriter out = new PrintWriter(
                    new FileWriter("Coins"+".txt"));//i++ for different file name
            out.println(this.qoh + " quarters on hand");
            out.println(this.doh + " dimes on hand");
            out.println(this.noh + " nickels on hand");
            out.println(this.poh + " pennies on hand");
            out.close();}catch (IOException e) {
            this.errmsg = "Error saving coins: " + e.getMessage();

        }
    }

}
