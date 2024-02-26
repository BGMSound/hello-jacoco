package kr.bgmsound.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestRepositoryTests {

    private static TestRepository testRepository;

    @BeforeAll
    public static void setUp() {
        testRepository = new InMemoryTestRepository();
    }

    @Test
    public void testSave() {
        testRepository.save("key", "value");
        assertThat(testRepository.findByKey("key")).isNotEmpty();
    }

    @Test
    public void testFindByKey() {
        testRepository.save("key", "value");
        assertThat(testRepository.findByKey("key")).get().isEqualTo("value");
    }

    @Test
    public void testDeleteByKey() {
        testRepository.save("key", "value");
        assertThat(testRepository.findByKey("key")).isNotEmpty();
        testRepository.deleteByKey("key");
        assertThat(testRepository.findByKey("key")).isEmpty();
    }
}
