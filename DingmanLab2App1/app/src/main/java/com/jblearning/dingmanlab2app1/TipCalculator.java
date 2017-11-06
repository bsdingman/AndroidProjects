package com.jblearning.dingmanlab2app1;

public class TipCalculator {
    private float tip;
    private float bill;
    private int guests;

    public TipCalculator(float newTip, float newBill, int newGuests ) {
        setTip( newTip );
        setBill( newBill );
        setGuests(newGuests);
    }

    public float getTip( ) {
        return tip;
    }

    public float getBill( ) {
        return bill;
    }

    public void setTip( float newTip ) {
        if( newTip > 0 )
            tip = newTip;
    }

    public void setBill( float newBill ) {
        if( newBill > 0 )
            bill = newBill;
    }

    public void setGuests( int newGuests)
    {
        if (newGuests > 0)
            this.guests = newGuests;
    }

    public float tipAmount( ) {
        return (bill * tip) / guests;
    }

    public float totalAmount( ) {
        return (bill + tipAmount()) / guests;
    }
}
