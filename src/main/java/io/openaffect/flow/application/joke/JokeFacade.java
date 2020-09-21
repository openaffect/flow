package io.openaffect.flow.application.joke;


import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.domain.joke.Joke;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JokeFacade {

  private IJokeRepository jokeRepository;

  public JokeFacade(IJokeRepository jokeRepository) {
    this.jokeRepository = jokeRepository;
  }

  public void proposeJoke(ProposeJokeCommand command) {
    Joke submittedJoke = Joke.builder()
      .author(command.getAuthor())
      .text(command.getText())
      .build();
    jokeRepository.save(submittedJoke);
  }

  public JokesDTO getJokes(JokesQuery query) {
    Collection<Joke> allJokes = jokeRepository.find(query);

    List<JokesDTO.JokeDTO> allJokesDTO = allJokes.stream().map(joke -> JokesDTO.JokeDTO.builder()
      .text(joke.getText())
      .build()).collect(Collectors.toList());

    return JokesDTO.builder()
      .jokes(allJokesDTO)
      .build();
  }

}
