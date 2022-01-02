package bco.archunitrules.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.DisplayName;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "bco.archunitrules")
@DisplayName("Vérification de l'architecture des domaines")
public class DomainTest {
    @ArchTest
    static final ArchRule les_classes_du_domaine_ne_doivent_dependre_d_aucune_classe_de_l_infra = noClasses()
            .that().resideInAnyPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..infra..");

    @ArchTest
    static final ArchRule les_classes_d_un_domaine_ne_doivent_dependre_d_aucune_classe_de_d_autres_domaines = SlicesRuleDefinition
            .slices()
            .matching("..(*).domain")
            .should()
            .notDependOnEachOther();
}
