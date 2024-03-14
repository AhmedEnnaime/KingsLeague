import { createSlice } from "@reduxjs/toolkit";
import ITournament from "../../interfaces/ITournament";
import {
  createTournament,
  deleteTournament,
  fetchAllTournaments,
  fetchTournamentById,
  updateTournament,
} from "./tournamentActions";

interface TournamentState {
  tournaments: ITournament[];
  selectedTournament: ITournament | null;
  loading: boolean;
}

const initialState: TournamentState = {
  tournaments: [],
  selectedTournament: null,
  loading: false,
};

const tournamentSlice = createSlice({
  name: "tournament",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllTournaments.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllTournaments.fulfilled, (state, action) => {
        state.loading = false;
        state.tournaments = action.payload;
      })
      .addCase(fetchTournamentById.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTournamentById.fulfilled, (state, action) => {
        state.loading = false;
        state.selectedTournament = action.payload;
      })
      .addCase(createTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(createTournament.fulfilled, (state, action) => {
        state.loading = false;
        state.tournaments.push(action.payload);
      })
      .addCase(deleteTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteTournament.fulfilled, (state, action) => {
        state.loading = false;
        state.tournaments = state.tournaments.filter(
          (tournament) => tournament.id !== action.meta.arg
        );
      })
      .addCase(updateTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateTournament.fulfilled, (state, action) => {
        state.loading = false;
        const updatedTournamentId = action.meta.arg.id;
        const updatedTournament = action.payload;
        const existingTournament = state.tournaments.find(
          (tournament) => tournament.id === updatedTournamentId
        );
        if (existingTournament) {
          Object.assign(existingTournament, updatedTournament);
        }
      });
  },
  reducers: {},
});

export default tournamentSlice.reducer;
