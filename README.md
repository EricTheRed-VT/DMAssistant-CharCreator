# DMAssistant-CharCreator

Character Creation Application and Associated Classes

updated 5/6/2016

Thank you for your interest in this project! It is ongoing and still in pre-alpha development, but feel free to poke through my source code. Please send me any questions, notes, or recommendations.

I began this Charactor Creator Application as a way to test and demonstrate my programming skills. It requires firstly the reverse-engineering of the Dungeons & Dragons rules-structure, and secondly the modular integration of many interdependent systems, while maintaining high customizability. I plan to eventually create a full virtual-tabletop application, which will handle character creation, combat, and all other calculations involved in the game.

**Languages & Systems Used**  
 I am currently using IntelliJ IDEA with SceneBuilder and DataGrip. The application uses Java 8, a JavaFX GUI, and a SQLite database. Using the latest version of Java allows for lambda expressions and built-in JavaFX libraries, including extensive use of properties, binding, and listeners. JavaFX allows for simpler coding and a much more modern interface compared to Swing; layout is designed in FXML and a custom CSS will be applied after construction. The database and GUI are accessed through dedicated controller classes; JSON or XML will be used to transfer the data beween users.

**Style Notes**  
I have found that hierarchical abstract classes are more fitting than interfaces for this project, but I constantly abstract/generalize after working out concrete/specific cases (you could say I'm progressing toward a more SOLID GRASP of the material). This comes from the necessity of reverse-engineering the published D&D tables/systems.


Speaking of which:
*(All rights to Dungeons & Dragons rules and materials belong to Wizards of the Coast, and are used here solely for the purpose of education and demonstration.)*
