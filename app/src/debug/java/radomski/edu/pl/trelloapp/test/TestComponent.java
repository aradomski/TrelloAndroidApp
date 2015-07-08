package radomski.edu.pl.trelloapp.test;

import javax.inject.Singleton;

import radomski.edu.pl.trelloapp.api.board.BoardApi;
import radomski.edu.pl.trelloapp.api.boards.BoardsApi;

@Singleton
@dagger.Component(
        modules = {
                TestModule.class,
        })
public interface TestComponent {
    BoardApi boardApi();

    BoardsApi boardsApi();
}