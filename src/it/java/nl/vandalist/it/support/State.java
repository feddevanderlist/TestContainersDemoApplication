package nl.vandalist.it.support;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Met een state object kunnen we binnen een scenario state delen.
 * Dit object wordt beheerd in de ApplicationContext van Spring dankzij de @Component annotatie.
 * En omdat we de @ScenarioScope annotatie hebben toegevoegd, wordt hij na ieder scenario geleegd.
 * Zie {@link SchoolStepMetState} voor hoe je een dergelijk state object gebruikt.
 * <p/>
 * Momenteel bevat deze class slechts 1 veld.
 * Maar je kunt hem uitbreiden met alle properties die je nodig hebt.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ScenarioScope // Met deze annotatie zorgen we ervoor dat het state object automatisch wordt geleegd na een scenario
public class State {
    private Response response;
}