import lombok.*;

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
}
