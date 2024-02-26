package kr.bgmsound;

import kr.bgmsound.controller.TestDispatcherController;
import kr.bgmsound.controller.mapping.TestDeleteController;
import kr.bgmsound.controller.mapping.TestGetController;
import kr.bgmsound.controller.mapping.TestPostController;
import kr.bgmsound.repository.InMemoryTestRepository;
import kr.bgmsound.repository.TestRepository;
import kr.bgmsound.service.TestService;
import kr.bgmsound.service.TestServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TestRepository testRepository = new InMemoryTestRepository();

        TestService testService = new TestServiceImpl(testRepository);

        TestGetController testGetController = new TestGetController(testService);
        TestPostController testPostController = new TestPostController(testService);
        TestDeleteController testDeleteController = new TestDeleteController(testService);

        TestDispatcherController testDispatcherController = new TestDispatcherController(testGetController, testPostController, testDeleteController);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            testDispatcherController.testDispatch(command);
        }
    }
}