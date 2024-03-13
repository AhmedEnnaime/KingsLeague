import { createSlice } from "@reduxjs/toolkit";
import ITournament from "../../interfaces/ITournament";
import { fetchAllTournaments } from "./tournamentActions";

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
      });
  },
  reducers: {},
});

export default tournamentSlice.reducer;
