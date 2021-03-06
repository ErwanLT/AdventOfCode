import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class Jour4Test {

    @Test
    public void testColorRegex(){
        Assert.assertTrue(colorRegex("#4287f5"));
        Assert.assertTrue(colorRegex("#4287B5"));
        Assert.assertFalse(colorRegex("4287B5"));
        Assert.assertFalse(colorRegex("#4287K5"));
    }

    @Test
    public void testPassportIDRegex(){
        Assert.assertTrue(passportIdRegex("000000001"));
        Assert.assertFalse(passportIdRegex("0123456789"));
    }

    @Test
    public void testEyesColorRegex(){
        Assert.assertFalse(eyesColorRegex("wat"));
        Assert.assertTrue(eyesColorRegex("brn"));
    }

    @Test
    public void testHeight(){
        Assert.assertTrue(validateHeight("60in"));
        Assert.assertTrue(validateHeight("190cm"));
        Assert.assertFalse(validateHeight("190"));
        Assert.assertFalse(validateHeight("60"));
    }

    @Test
    public void testBirthYear(){
        Assert.assertTrue(validateBirthYear("2002"));
        Assert.assertFalse(validateBirthYear("2003"));
    }

    @Test
    public void testIssueYear(){
        Assert.assertFalse(validateIssueYear("2002"));
        Assert.assertFalse(validateIssueYear("2040"));
        Assert.assertTrue(validateIssueYear("2015"));
    }

    @Test
    public void testExpirationYear(){
        Assert.assertFalse(validateExpirationYear("2002"));
        Assert.assertFalse(validateExpirationYear("2040"));
        Assert.assertTrue(validateExpirationYear("2023"));
    }

    @Test
    public void testPassport(){
        Passport p = new Passport();
        p.setBirthYear("2018");
        p.setIssueYear("1926");
        p.setExpirationYear("1972");
        p.setHairColor("#18171d");
        p.setEyeColor("amb");
        p.setHeight("170");
        p.setPassportID("186cm");
        Assert.assertFalse(validatePassport(p));

        p.setBirthYear("1980");
        p.setIssueYear("2012");
        p.setExpirationYear("2030");
        p.setHairColor("#623a2f");
        p.setEyeColor("grn");
        p.setHeight("74in");
        p.setPassportID("087499704");
        Assert.assertTrue(validatePassport(p));

        p.setBirthYear("2007");
        p.setIssueYear("2003");
        p.setExpirationYear("2038");
        p.setHairColor("74454a");
        p.setEyeColor("zzz");
        p.setHeight("59cm");
        p.setPassportID("3556412378");
        Assert.assertFalse(validatePassport(p));
    }

    private boolean validatePassport(Passport p) {
        return validateBirthYear(p.getBirthYear()) && validateIssueYear(p.getIssueYear()) && validateExpirationYear(p.getExpirationYear())
                && colorRegex(p.getHairColor()) && eyesColorRegex(p.getEyeColor()) && validateHeight(p.getHeight()) && passportIdRegex(p.getPassportID());
    }

    private boolean colorRegex(String s) {
        return s.matches("^(#[a-fA-F0-9]{6}$)");
    }

    private boolean passportIdRegex(String s){
        return s.matches("^[0-9]{9}$");
    }

    private boolean eyesColorRegex(String s){
        return s.matches("^((amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth))$");
    }

    private boolean validateHeight(String s){
        final Pattern pattern = Pattern.compile("^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$");
        return pattern.matcher(s).matches();
    }

    private boolean validateBirthYear(String s){
        final Pattern pattern = Pattern.compile("^(200[0-2]|19[2-9][0-9])$");
        return pattern.matcher(s).matches();
    }

    private static boolean validateIssueYear(String s){
        final Pattern pattern = Pattern.compile("^(2020|201[0-9])$");
        return pattern.matcher(s).matches();
    }

    private static boolean validateExpirationYear(String s){
        final Pattern pattern = Pattern.compile("^(2030|202[0-9])$");
        return pattern.matcher(s).matches();
    }
}
