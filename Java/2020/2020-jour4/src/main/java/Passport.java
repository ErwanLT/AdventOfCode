import lombok.*;

import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passport {

    private String birthYear;

    private String issueYear;

    private String expirationYear;

    private String height;

    private String hairColor;

    private String eyeColor;

    private String passportID;

    private String countryID;

    public boolean isValid(){
        return validateBirthYear() && validateIssueYear() && validateExpirationYear() && valiHeight() && validateHairColor() && validateEyesColor() && validatePassportId();
    }

    private boolean validatePassportId() {
        final Pattern pattern = Pattern.compile("^[0-9]{9}$");
        return pattern.matcher(this.passportID).matches();
    }

    private boolean validateHairColor() {
        final Pattern pattern = Pattern.compile("^(#[0-9a-f]{6})$");
        return pattern.matcher(this.hairColor).matches();
    }

    private boolean validateEyesColor() {
        final Pattern pattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
        return pattern.matcher(this.eyeColor).matches();
    }

    private boolean valiHeight() {
        if(this.height.contains("cm")){
            int h = Integer.valueOf(this.height.split("cm")[0]);
            return h >= 150 && h <= 193;
        } else if(this.height.contains("in")){
            int h = Integer.valueOf(this.height.split("in")[0]);
            return h >= 59 && h <= 76;
        } else {
            return false;
        }
    }

    private boolean validateExpirationYear(){
        if (this.expirationYear.length() != 4){
            return false;
        }
        int expiration = Integer.valueOf(this.expirationYear);
        return expiration >= 2020 && expiration <= 2030;
    }

    private boolean validateIssueYear(){
        if (this.issueYear.length() != 4){
            return false;
        }
        int issue = Integer.valueOf(this.issueYear);
        return issue >= 2010 && issue <= 2020;
    }

    private boolean validateBirthYear(){
        if (this.birthYear.length() != 4){
            return false;
        }
        int birth = Integer.valueOf(this.birthYear);
        return birth >= 1920 && birth <= 2002;
    }
}