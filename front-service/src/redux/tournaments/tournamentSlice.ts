import { createSlice } from "@reduxjs/toolkit";
import ITournament from "../../interfaces/ITournament";
import {
  createTournament,
  deleteTournament,
  fetchAllTournaments,
  updateTournament,
} from "./tournamentActions";

interface TournamentState {
  tournaments: ITournament[];
  loading: boolean;
}

const initialState: TournamentState = {
  tournaments: [],
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
      .addCase(createTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(createTournament.fulfilled, (state, action) => {
        state.loading = false;
        state.tournaments.push(action.payload);
      })
      .addCase(deleteTournament.pending, (state, action) => {
        state.loading = true;
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
