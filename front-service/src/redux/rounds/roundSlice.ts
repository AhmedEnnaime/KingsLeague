import { createSlice } from "@reduxjs/toolkit";
import IRound from "../../interfaces/IRound";
import { createRound, fetchRoundsByTournamentId } from "./roundActions";

interface RoundState {
  rounds: IRound[];
  loading: boolean;
}

const initialState: RoundState = {
  rounds: [],
  loading: false,
};

const roundSlice = createSlice({
  name: "round",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchRoundsByTournamentId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchRoundsByTournamentId.fulfilled, (state, action) => {
        state.loading = false;
        state.rounds = action.payload;
      })
      .addCase(createRound.pending, (state) => {
        state.loading = true;
      })
      .addCase(createRound.fulfilled, (state, action) => {
        state.loading = false;
        state.rounds.push(action.payload);
      });
  },
  reducers: {},
});

export default roundSlice.reducer;
