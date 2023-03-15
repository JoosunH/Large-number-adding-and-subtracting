/**
 * Author: Devin Lo
 * The author acknowledges that the students also wrote and shared their own unit tests, and asked great questions regarding the assignment requirements.
 * Their test and questions have directly inspired some of the unit tests in this file.
 * Created for York University, EECS 2011, Winter 2023, Sections M and N
 */

package a1;

import org.junit.Test;
import static org.junit.Assert.*;

import a1.LargeIntInterface;
import a1.LargeInt;
import a1.SpecializedList;

public class LargeIntTest {
    public static final int TIMEOUT = 200;

    public String clean(LargeInt rawInt) {
        return rawInt.toString().replace("+","").replace(",","");
    }

    @Test
    // @Description(description="LargeInt 1 addition: add 8 and 4")
    public void simpleAddition() {
        LargeInt a = new LargeInt("8");
        LargeInt b = new LargeInt("4");
        LargeInt sum = a.add(b);
        assertEquals("12", clean(sum));
    }

    @Test
    // @Description(description="LargeInt 2 addition: add -41 and -32")
    public void addTwoNegatives() {
        LargeInt a = new LargeInt("-41");
        LargeInt b = new LargeInt("-32");
        LargeInt sum = a.add(b);
        assertEquals("-73", clean(sum));
    }

    @Test
    // @Description(description="LargeInt 3 addition: add -38 and 20")
    public void addNegativeAndPositive() {
        LargeInt a = new LargeInt("-38");
        LargeInt b = new LargeInt("20");
        LargeInt sum = a.add(b);
        assertEquals("-18", clean(sum));
    }

    @Test
    // @Description(description="LargeInt 4 addition: add 999999 and 1")
    public void overflow() {
        LargeInt a = new LargeInt("999999");
        LargeInt b = new LargeInt("1");
        LargeInt sum = a.add(b);
        assertEquals("1000000", clean(sum));
    }

    @Test
    // @Description(description="LargeInt 5 subtraction: 8 minus 4")
    public void simpleSubtraction() {
        LargeInt a = new LargeInt("8");
        LargeInt b = new LargeInt("4");
        LargeInt result = a.subtract(b);
        assertEquals("4", clean(result));
    }

    @Test
    // @Description(description="LargeInt 6 subtraction: -58 minus -190")
    public void subtractTwoNegatives() {
        LargeInt a = new LargeInt("-58");
        LargeInt b = new LargeInt("-190");
        LargeInt result = a.subtract(b);
        assertEquals("132", clean(result));
    }

    @Test
    // @Description(description="LargeInt 7 substraction: -89 minus 4")
    public void subtractNegativeAndPositive() {
        LargeInt a = new LargeInt("-89");
        LargeInt b = new LargeInt("4");
        LargeInt result = a.subtract(b);
        assertEquals("-93", clean(result));
    }

    @Test
    // @Description(description="LargeInt 8 subtraction of same number")
    public void subtractSame() {
        LargeInt a = new LargeInt("1850913");
        LargeInt b = new LargeInt("1850913");
        LargeInt result = a.subtract(b);
        assertEquals("0", clean(result));
    }

    @Test
    // @Description(description="LargeInt 9 addition of three large numbers")
    public void largeAdditionTripletMixed() {
        String aString = "-29571823096";
        String bString = "76247560791";
        String cString = "-31450646";
        LargeInt a = new LargeInt(aString);
        LargeInt b = new LargeInt(bString);
        LargeInt c = new LargeInt("-31450646");

        LargeInt sum = a.add(b).add(c);

        assertEquals("46644287049", clean(sum));
        assertEquals(aString, clean(a));
        assertEquals(bString, clean(b));
        assertEquals(cString, clean(c));
    }

    @Test
    // @Description(description="LargeInt 10 subtraction of three large numbers")
    public void largeSubtractionTripletMixed() {
        String aString = "-29571823096";
        String bString = "76247560791";
        String cString = "-31450646";
        LargeInt a = new LargeInt(aString);
        LargeInt b = new LargeInt(bString);
        LargeInt c = new LargeInt("-31450646");

        LargeInt result = a.subtract(b).subtract(c);

        assertEquals("-105787933241", clean(result));
        assertEquals(aString, clean(a));
        assertEquals(bString, clean(b));
        assertEquals(cString, clean(c));
    }

    @Test
    // @Description(description="LargeInt 11 test setNegative()")
    public void testSetNegative() {
        String aString = "246";
        LargeInt a = new LargeInt(aString);

        a.setNegative();

        assertNotEquals(aString, clean(a));
        assertEquals("-246", clean(a));
    }

    @Test
    // @Description(description="LargeInt 12 test setNegtive then add numbers")
    public void setNegativeAndAdd() {
        String aString = "-29571823096";
        String bString = "76247560791";
        String cString = "-31450646";
        LargeInt a = new LargeInt(aString);
        LargeInt b = new LargeInt(bString);
        LargeInt c = new LargeInt("-31450646");

        b.setNegative();
        c.setNegative();

        LargeInt sum = a.add(b).add(c);

        assertEquals("-105787933241", clean(sum));
        assertEquals(aString, clean(a));
        assertNotEquals(bString, clean(b));
        assertNotEquals(cString, clean(c));
    }

    @Test
    // @Description(description="LargeInt 13 test setNegative then subtract numbers")
    public void setNegativeAndSubtract() {
        String aString = "-29571823096";
        String bString = "76247560791";
        String cString = "-31450646";
        LargeInt a = new LargeInt(aString);
        LargeInt b = new LargeInt(bString);
        LargeInt c = new LargeInt("-31450646");

        a.setNegative();
        c.setNegative();

        LargeInt result = a.subtract(b).subtract(c);

        assertEquals("-46707188341", clean(result));
        assertNotEquals(aString, clean(a));
        assertEquals(bString, clean(b));
        assertNotEquals(cString, clean(c));
    }

    /**
     * It is implied that the commutative property tests have also passed
     */
    @Test
    // @Description(description="LargeInt 14 test add several numbers")
    public void testAddAssociativeProperty() {
        String oneValue = "1";
        String twoValue = "2";
        String threeValue = "3";
        String fourValue = "4";
        String sixValue = "6";
        String sevenValue = "7";

        LargeInt one = new LargeInt(oneValue);
        LargeInt two = new LargeInt(twoValue);
        LargeInt four = new LargeInt(fourValue);

        // (1+2)+4
        LargeInt onePlusTwo = one.add(two);
        assertEquals(threeValue, clean(onePlusTwo));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(one));
        assertEquals(twoValue, clean(two));
        LargeInt onePlusTwoPlusFour = onePlusTwo.add(four);
        assertEquals(sevenValue, clean(onePlusTwoPlusFour));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(onePlusTwo));
        assertEquals(fourValue, clean(four));

        // 1+(2+4)
        LargeInt twoPlusFour = two.add(four);
        assertEquals(sixValue, clean(twoPlusFour));
        // ensure original LargeInts state didn't change
        assertEquals(twoValue, clean(two));
        assertEquals(fourValue, clean(four));
        LargeInt twoPlusFourPlusOne = one.add(twoPlusFour);
        assertEquals(sevenValue, clean(twoPlusFourPlusOne));
        // ensure original LargeInts state didn't change
        assertEquals(sixValue, clean(twoPlusFour));
        assertEquals(oneValue, clean(one));

        // now assert that (1+2)+4 = 1+(2+4)
        assertEquals(onePlusTwoPlusFour.toString().replace("+","").replace(",", ""), clean(twoPlusFourPlusOne));
    }

    /**
     * The purpose of these multi-add/subtract tests is to ensure that the LargeInt you produced
     * can actually be used properly
     */
    @Test
    // @Description(description="LargeInt 15 test add multiple numbers")
    public void testMultiAdd() {
        String oneValue = "1";
        String twoValue = "2";
        String threeValue = "3";
        String fourValue = "4";
        String fiveValue = "5";
        LargeInt me = new LargeInt(oneValue);
        LargeInt me2 = new LargeInt(twoValue);
        LargeInt me3 = me.add(me2);
        assertEquals(threeValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(twoValue, clean(me2));
        LargeInt me4 = me.add(me3);
        assertEquals(fourValue, clean(me4));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(threeValue, clean(me3));
        LargeInt me5 = me4.add(me);
        assertEquals(fiveValue, clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(fourValue, clean(me4));

        // now, try using two generated LargeInts
        LargeInt me6 = me3.add(me5);
        assertEquals("8", clean(me6));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fiveValue, clean(me5));
    }

    @Test
    // @Description(description="LargeInt 16 test subtract multiple numbers")
    public void testMultiSubtract() {
        String oneValue = "1";
        String twoValue = "2";
        String threeValue = "3";
        String fourValue = "4";
        String fiveValue = "5";
        LargeInt me = new LargeInt(fiveValue);
        LargeInt me2 = new LargeInt(oneValue);
        LargeInt me3 = me.subtract(me2);

        assertEquals(fourValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(fiveValue, clean(me));
        assertEquals(oneValue, clean(me2));

        LargeInt me4 = me3.subtract(me2);
        assertEquals(threeValue, clean(me4));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me2));
        assertEquals(fourValue, clean(me3));

        LargeInt me5 = me4.subtract(me2);
        assertEquals(twoValue, clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me2));
        assertEquals(threeValue, clean(me4));

        // now, try using two generated LargeInts
        LargeInt me6 = me3.subtract(me5);
        assertEquals("2", clean(me6));
        // ensure original LargeInts state didn't change
        assertEquals(fourValue, clean(me3));
        assertEquals(twoValue, clean(me5));
    }

    @Test
    // @Description(description="LargeInt 17 test add then subtract several numbers")
    public void testMultiAddThenSubtract() {
        String twoValue = "2";
        String eightValue = "8";
        String tenValue = "10";
        String threeValue = "3";

        LargeInt me = new LargeInt(twoValue);
        LargeInt me2 = new LargeInt(eightValue);
        LargeInt me3 = me.add(me2);
        assertEquals(tenValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(twoValue, clean(me));
        assertEquals(eightValue, clean(me2));

        LargeInt me4 = new LargeInt(threeValue);

        LargeInt me5 = me3.subtract(me4);
        assertEquals("7", clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(tenValue, clean(me3));
        assertEquals(threeValue, clean(me4));
    }

    @Test
    // @Description(description="LargeInt 18 test subtract then add several numbers")
    public void testMultiSubtractThenAdd() {
        String eightValue = "8";
        String sixValue = "6";
        String twoValue = "2";
        String threeValue = "3";

        LargeInt me = new LargeInt(eightValue);
        LargeInt me2 = new LargeInt(sixValue);
        LargeInt me3 = me.subtract(me2);
        assertEquals(twoValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(eightValue, clean(me));
        assertEquals(sixValue, clean(me2));

        LargeInt me4 = new LargeInt(threeValue);

        LargeInt me5 = me3.add(me4);
        assertEquals("5", clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(twoValue, clean(me3));
        assertEquals(threeValue, clean(me4));
    }

    /**
     * The following tests are the multi-add/subtract tests combined with values from Hiraku's huge numbers tests
     */

    @Test
    // @Description(description="LargeInt 19 test add multiple huge numbers")
    public void testMultiAddHUGE() {
        String oneValue = "23783743841734789134626343768437462378463784623487378439324983992372394379491347473927413794791374979314792379479234792379423794792379473923689423689428346";
        String twoValue = "9929297433971793793714012074237124791247177694913723794791374379273792317945237947923794237952357463647723463564523574723545235742357237682346";
        String threeValue = "23783743841744718432060315562231176390538021748278625617019897716167185753870621266245359032739298773552744736942882515842988318367103019159431780927110692";
        String fourValue = "47567487683479507566686659330668638769001806371766004056344881708539580133361968740172772827530673752867537116422117308222412113159482493083121204616539038";
        String fiveValue = "71351231525214296701313003099106101147465590995253382495669865700911974512853316214100186622322048732182329495901352100601835907951861967006810628305967384";
        LargeInt me = new LargeInt(oneValue);
        LargeInt me2 = new LargeInt(twoValue);
        LargeInt me3 = me.add(me2);
        assertEquals(threeValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(twoValue, clean(me2));
        LargeInt me4 = me.add(me3);
        assertEquals(fourValue, clean(me4));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(threeValue, clean(me3));
        LargeInt me5 = me4.add(me);
        assertEquals(fiveValue, clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(fourValue, clean(me4));

        // now, try using two generated LargeInts
        LargeInt me6 = me3.add(me5);
        assertEquals("95134975366959015133373318661337277538003612743532008112689763417079160266723937480345545655061347505735074232844234616444824226318964986166242409233078076", me6.toString().replace("+","").replace(",", ""));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fiveValue, clean(me5));
    }

    @Test
    // @Description(description="LargeInt 20 test subtract multiple huge numbers")
    public void testMultiSubtractHUGE() {
        String oneValue = "228928929895898958938923895895389598589893589523893528958923895328958935892358952823835892895382539855757934279727";
        String twoValue = "2387230952375237324734672347657284782587724564785854828523478524523465276835828375";
        String threeValue = "228928929895898958938923895895387211358941214286568794286576238044176348167794166969007369416858016390481098451352";
        String fourValue = "237283472134723070237507235702307523070723752307520783057820357023032203082332";
        String fiveValue = "228928929895898958938923895895387211121657742151845724049069002341868825097070414661486586359037659367448895369020";
        LargeInt me = new LargeInt(oneValue);
        LargeInt me2 = new LargeInt(twoValue);
        LargeInt me3 = me.subtract(me2);
        assertEquals(threeValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(twoValue, clean(me2));

        LargeInt me4 = new LargeInt(fourValue);
        LargeInt me5 = me3.subtract(me4);
        assertEquals(fiveValue, clean(me5));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fourValue, clean(me4));

        // now, try using two generated LargeInts
        LargeInt me6 = me3.subtract(me5);
        assertEquals("237283472134723070237507235702307523070723752307520783057820357023032203082332", me6.toString().replace("+","").replace(",", ""));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fiveValue, clean(me5));
    }

    @Test
    // @Description(description="LargeInt 21 test add then subtract huge number")
    public void testMultiAddThenSubtractHUGE() {
        String oneValue = "94899384934238723788742783723787882378782783757238785278378237857626348562486568246526485682468568234656824682345623568523668235";
        String twoValue = "4756237642357457234235742375745234523574523452357452374523574235457235723575234455234574235723577523523457423573457234";
        String threeValue = "94899384938994961431100240958023624754528018280813308730735690232149922797943803970101720137703142470380402205869080992097125469";
        String fourValue = "2387230952375237324734672347657284782587724564785854828523478524523465276835828375";

        LargeInt me = new LargeInt(oneValue);
        LargeInt me2 = new LargeInt(twoValue);
        LargeInt me3 = me.add(me2);
        assertEquals(threeValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, clean(me));
        assertEquals(twoValue, clean(me2));

        LargeInt me4 = new LargeInt(fourValue);

        LargeInt me5 = me3.subtract(me4);
        assertEquals("94899384938994961431100240958023624754528018278426077778360452907415250450286519187513995572917287641856923681345615715261297094", me5.toString().replace("+","").replace(",", ""));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fourValue, me4.toString().replace("+","").replace(",", ""));
    }

    @Test
    // @Description(description="LargeInt 22 test subtract then add huge number")
    public void testMultiSubtractThenAddHUGE() {
        String oneValue = "71351231525214296701313003099106101147465590995253382495669865700911974512853316214100186622322048732182329495901352100601835907951861967006810628305967384";
        String twoValue = "237283472134723070237507235702307523070723752307520783057820357023032203082332";
        String threeValue = "71351231525214296701313003099106101147465590995253382495669865700911974512853078930628051899251811224946627188378281376849528387168804146649787596102885052";
        String fourValue = "2387230952375237324734672347657284782587724564785854828523478524523465276835828375";

        LargeInt me = new LargeInt(oneValue);
        LargeInt me2 = new LargeInt(twoValue);
        LargeInt me3 = me.subtract(me2);
        assertEquals(threeValue, clean(me3));
        // ensure original LargeInts state didn't change
        assertEquals(oneValue, me.toString().replace("+","").replace(",", ""));
        assertEquals(twoValue, me2.toString().replace("+","").replace(",", ""));

        LargeInt me4 = new LargeInt(fourValue);

        LargeInt me5 = me3.add(me4);
        assertEquals("71351231525214296701313003099106101147465590995253382495669865700911974515240309883003289223986483572603911970966005941635383215692282671173252872938713427", me5.toString().replace("+","").replace(",", ""));
        // ensure original LargeInts state didn't change
        assertEquals(threeValue, clean(me3));
        assertEquals(fourValue, me4.toString().replace("+","").replace(",", ""));
    }

    // finally, some memes

    /**
     * https://www.youtube.com/watch?v=eB5VXJXxnNU
     */
    @Test
    // @Description(description="LargeInt 23 test")
    public void testAliG() {
        // Yo.
        // Science. Wot is it all about?
        // Techmology. Wot is that all about?
        // Is it good? Or is it whack?
        String iIsTheValue = "9999999999999999999999999999999999999";
        String yoIAintFinishYouDunnoKnowWhatIWasGonnaSay = "9999999998999999998899889999999999999"; // there's supposed to be a decimal in this but we is doing integers
        // we ain't got multiply so we do an add instead
        LargeInt asYouEver = new LargeInt(iIsTheValue);
        LargeInt eatenABanana = new LargeInt(yoIAintFinishYouDunnoKnowWhatIWasGonnaSay);
        LargeInt thatsProof = asYouEver.add(eatenABanana);
        assertEquals("19999999998999999998899889999999999998", thatsProof.toString().replace("+","").replace(",", ""));
        // make sure you is flush the loo and not leave a floater backstage
        assertEquals(iIsTheValue, asYouEver.toString().replace("+","").replace(",", ""));
        assertEquals(yoIAintFinishYouDunnoKnowWhatIWasGonnaSay, eatenABanana.toString().replace("+","").replace(",", ""));
        // WICKED. i hope your computer aint blow up doin the compute

        // keep it real. respek
    }

    /**
     * https://vine.co/v/huwVJIEJW50
     */
    @Test
    // @Description(description="LargeInt 24 test")
    public void testUStoopid() {
        String no = "9";
        String imNot = "10";
        LargeInt whats = new LargeInt(no);
        LargeInt ninePlusTen = new LargeInt(imNot);
        LargeInt twentyOne = whats.add(ninePlusTen);
        String replace = twentyOne.toString().replace("+", "").replace(",", "");
        assertNotEquals("21", replace);
        assertEquals("19", replace);
        // check that u ain't stoopid and changed the starting number 9 to 11, or 10 to 12
        assertEquals(no, whats.toString().replace("+","").replace(",", ""));
        assertEquals(imNot, ninePlusTen.toString().replace("+","").replace(",", ""));
    }
}