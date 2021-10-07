Java code style = Google-Java-Style code was utilized.

Feature Set: Distinction (Pass & Credit & Distinction)

 - PASS: Implemented GUI application for both input and output API requests, successfully displaying its entity, storing its content into the Pastebin, and outputting the result as a Pastebin URL.

 - Credit: Successfully caching the entities in the local SQLite database. (Referred this link : https://edstem.org/courses/5417/discussion/501430)

 - Distinction: According to the property of Swing, its components can be accessed by only one thread at a time, generally, the event-dispatching thread. So, when I request for the list of the cryptocurrency, it takes a long time to request all the thousands of the cryptocurrencies from the list in API server and this blocks the thread, not allowing the user to do something else during the operation. Therefore, a new thread is created as the user tries to request for the list of the cryptocurrencies, hence making user to be able to do other operations simultaneously.

### How to Run ###

This project requires [Java 11.0.4](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
) or above, and [Gradle 5.6.1](https://gradle.org/releases/) or above.

How to login = for offline, you need to register first in order to login. (register -> click api key register -> ctrl+v to paste)

               for online, you just need to enter your own API keys without doing a register

When executing the program:

 - Examples for the values for each paramenter:
        
       id: 1, 2...
  
       slug: bitcoin, ethereum ...
 
       symbol: BTC, ETH...

For ViewCryptoCurrency menu (a price conversion), you need to write baseCurrency as well as quoteCurrency (e.g BTC/ETH).
***The baseCurrency will not be set for the entire program and this is confirmed by Josh https://edstem.org/courses/5417/discussion/514412***
-------------------------------------------------------------------------------------------------------------------------------
Successfully applied MVC pattern and the login GUI is now working properly.
Short Description: The code structure is adjusted in order to apply MVC pattern more efficiently. The hyperlink is implemented for user registering.

RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/207b2031d05ebce8858f897937d40056d2c4448e
Description: The MVC pattern applied in this commit was weak and not firm. Also the proper api calls haven't been made at this stage.

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/4c128a7970a897380d17b3c0c63f9732b7a457d1
Description: The MVC pattern became more firm and structurelized. The API calls is starting to be made in Process classes.
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/4c128a7970a897380d17b3c0c63f9732b7a457d1
Description: The login feature was barely implemented and others hadn't been implemented well.

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/92852d9b29b20967ebbfaddab3fe27b280f14e7f
Description: The login and calling api methods were fully implemented.
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/92852d9b29b20967ebbfaddab3fe27b280f14e7f
Description: The login was fully implemented but Web API calls were very weakly implemented

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/5a85f9739b0707d7e2e99f58ff86208ac56d1c19
Description: All the functionalities of both input and output APIS work properly through GUI. However, futher clearance and
update in its codes for better performance required (REFACTOR stage since it is the stage for thinking of how to improve the exsisting code))
-------------------------------------------------------------------------------------------------------------------------------
GREEN : https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/3766d7a58dab79fc3b04dc075c36eb88f677f717#diff-4d2ab223a0fc889bd7c978c11d4a1a59R56-R71
Description: The loggin in function now has a charge on deciding whether to pass the test for api calls or not.

REFACTOR -> RED(it was trivial to commit the RED stage commit) -> GREEN (URL above in the GREEN section) : https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/babb80be5b40ba2a20cd16cdca330bf7c06d4f12#diff-4d2ab223a0fc889bd7c978c11d4a1a59R31-R37
Description:Needed to change the Main codes in order for the test cases not to pass when the user is not logged in (for realistic GUI).
And this was solved [REFACTOR -> RED(it was trivial to commit the RED stage commit) -> GREEN (URL above in the GREEN section)]
-------------------------------------------------------------------------------------------------------------------------------
GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/d81d6ab7a3a0b000aad0d96e0422e02a704972bc#diff-3549565e958b55371af01a36c7365df5
Description: SQL has been implemented

REFACTOR: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/d81d6ab7a3a0b000aad0d96e0422e02a704972bc#diff-dd2bb22fe135cb8e9339f50c3fd67525
Description: Added in order to fix the hovering issue for radiobutton
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/493ee81d4257c6a73f684272edce5a98cf568234#diff-3c7f260d0651eb1e2ea5f43e4611fb14R1-R51
Descripton: Output test cases are implemented but failing to pass

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/bf97b8e6f27cff4ddb54c51ecb80619ccfaa74d9#diff-3c7f260d0651eb1e2ea5f43e4611fb14R47-R59
Description: The login functions are refactored in order to pass the test cases. Also it was not implemented in the correct way so the
logging_in methods were failed but now they are passed.
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/a651de02951eb1a2e7f7f0fb83cbdf63bae6304d#diff-ad34443af5e733deb8d65bc0bc27a275L89-L96
Description: Long run code for JButton as well as the big list of data from webserver api call caused the JButton to freeze the GUI

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/a651de02951eb1a2e7f7f0fb83cbdf63bae6304d#diff-ad34443af5e733deb8d65bc0bc27a275L89-L96
Description: Applying concurrency using thread made another thread in order to make it thread-safe. Can now run other things while
displaying list method is running 
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/35ac64712abfd4ee09b3bb7fdad37e1b29960189#diff-3f97a2f9ef3c35bf5b38813258233005R1-R37
Description: Implemented the test case as a black-boxing test before I imeplement the Database class

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/365e5a46086cb017d619de8b73589ec1e05295bd#diff-3549565e958b55371af01a36c7365df5R1-R57
Description: Implemented the code and the test case for database is now passed
-------------------------------------------------------------------------------------------------------------------------------


Exam commits:


RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/cde05889a94858bc90e08ba9c6510e484c3e37cc#diff-ededb6c3575504b2623ed6079a18ae16R1-R27
Description: Implemented a testcase for TDD performance. Failing the test.

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/54b39223aaca69322ed9b4170f919bc407fbcbd3#diff-ceaf037e5c9cae142a0ca25fae4cfac0R24-R37
Descrioption: Setting the maximum value for the amount of the base currency with conditional logic made the test to be able to pass its tests
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/5fd7b24f4d6b01798562d212f1c1e6f4277df704#diff-447dc389b8c6aa04cbd4906af2e0386eR32-R40
Description: Failing to pass the valid input test for viewCryptoCurrency() api request

GREEN:https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/8681da355cc77f35e76f65ff3df92af677e78ed4#diff-447dc389b8c6aa04cbd4906af2e0386eR38-R39
Description: Applied log-in function and passed the test	
-------------------------------------------------------------------------------------------------------------------------------
RED: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/8298bd3573c6a13e03caa5bb063efd901206dde4#diff-447dc389b8c6aa04cbd4906af2e0386eR51-R71
Description: Tests for offlineOutput created

GREEN: https://github.sydney.edu.au/syoo5953/SCD2_2021_Exam/commit/8298bd3573c6a13e03caa5bb063efd901206dde4#diff-4a0662a4ec4ff06e900a5b5c7d7f14eaR219-R252
Descrioption: Made the new tests pass


Citation: 

Minh, N. H., & Minh, N. H. (2017). How to create hyperlink with JLabel in Java Swing. HyperLink. https://www.codejava.net/java-se/swing/how-to-create-hyperlink-with-jlabel-in-java-swing

JList List Page Pagination - Programmer Sought. (2021). Pagination. https://www.programmersought.com/article/3093312909/


### SIMPLE EXTENSIONS REQUIRED ###
- Due to having difficulties of doing this assignment because of my personal (part-time-job) amnd public (online learning) circumstances,
the simple extensions is truly needed.

### Special Consideration submitted ###