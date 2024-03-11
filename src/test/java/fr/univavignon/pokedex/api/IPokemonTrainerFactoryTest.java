public void verifyTrainerCreatedIsCorrect() {
        String nomTrainer = "Lance";
        Team choixTeam = Team.VALOR;

        lenient().when(factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory)).thenReturn(expectTrainerMock);

        PokemonTrainer newTrainer = factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        verify(factoryTrainerMock).createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        assertEquals(expectTrainerMock, newTrainer, "The created trainer should match the expected mock instance.");
        }