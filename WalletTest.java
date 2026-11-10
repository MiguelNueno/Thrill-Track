import junit.framework.TestCase;

/**
 *  Tests the Wallet class
 * 
 *  @author nanaababio
 *  @version Nov 10, 2026
 */
public class WalletTest
    extends TestCase
{
   
    //~ Fields ................................................................
    private Wallet wallet;

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    
    public void setUp()
    {
        wallet = new Wallet(20, 10);
    }
    
    
    /**
     * tests the initial cash and ticket balances
     */
    public void testInitialBalances()
    {
        assertEquals(20, wallet.getCashBalance());
        assertEquals(10, wallet.getTicketBalance());
    }

    /**
     * tests adding cash to the wallet
     */
    public void testAddCash()
    {
        wallet.addCash(10);

        assertEquals(30, wallet.getCashBalance());
    }

    /**
     * tests purchasing tickets when enough cash is available
     */
    public void testBuyTickets()
    {
        boolean result = wallet.buyTickets(5, 2.0);

        assertTrue(result);
        assertEquals(10, wallet.getCashBalance());
        assertEquals(15, wallet.getTicketBalance());
    }

    /**
     * tests purchasing tickets using the exact available balance
     */
    public void testBuyTicketsWithExactBalance()
    {
        Wallet newWallet = new Wallet(10, 0);

        boolean result = newWallet.buyTickets(5, 2.0);

        assertTrue(result);
        assertEquals(0, newWallet.getCashBalance());
        assertEquals(5, newWallet.getTicketBalance());
    }

    /**
     * tests that a purchase fails when there is not enough cash
     */
    public void testBuyTicketsWithoutEnoughCash()
    {
        Wallet newWallet = new Wallet(5, 2);

        boolean result = newWallet.buyTickets(5, 2.0);

        assertFalse(result);
        assertEquals(5, newWallet.getCashBalance());
        assertEquals(2, newWallet.getTicketBalance());
    }

    /**
     * tests deducting tickets from the wallet
     */
    public void testDeductTickets()
    {
        boolean result = wallet.deductTickets(3);

        assertTrue(result);
        assertEquals(7, wallet.getTicketBalance());
    }

    /**
     * tests deducting all available tickets
     */
    public void testDeductAllTickets()
    {
        boolean result = wallet.deductTickets(10);

        assertTrue(result);
        assertEquals(0, wallet.getTicketBalance());
    }

    /**
     * tests that a guest cannot deduct more tickets than available
     */
    public void testDeductMoreTicketsThanAvailable()
    {
        boolean result = wallet.deductTickets(15);

        assertFalse(result);
        assertEquals(10, wallet.getTicketBalance());
    }

}
