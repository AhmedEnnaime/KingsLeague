import { createSlice } from "@reduxjs/toolkit";
import IMatch from "../../interfaces/IMatch";
import {
  createMatch,
  deleteMatch,
  fetchAllMatches,
  fetchMatchesByMatchDayId,
  fetchMatchesByRoundId,
  updateMatch,
} from "./matchActions";

interface MatchState {
  matches: IMatch[];
  loading: boolean;
}

const initialState: MatchState = {
  matches: [],
  loading: false,
};

const matchSlice = createSlice({
  name: "match",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllMatches.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllMatches.fulfilled, (state, action) => {
        state.loading = false;
        state.matches = action.payload;
      })
      .addCase(createMatch.pending, (state) => {
        state.loading = true;
      })
      .addCase(createMatch.fulfilled, (state, action) => {
        state.loading = false;
        state.matches.push(action.payload);
      })
      .addCase(deleteMatch.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteMatch.fulfilled, (state, action) => {
        state.loading = false;
        state.matches = state.matches.filter(
          (match) => match.id !== action.meta.arg
        );
      })
      .addCase(updateMatch.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateMatch.fulfilled, (state, action) => {
        state.loading = false;
        const updatedMatchId = action.meta.arg.id;
        const updatedMatch = action.payload;
        const existingMatch = state.matches.find(
          (match) => match.id === updatedMatchId
        );
        if (existingMatch) {
          Object.assign(existingMatch, updatedMatch);
        }
      })
      .addCase(fetchMatchesByMatchDayId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchMatchesByMatchDayId.fulfilled, (state, action) => {
        state.loading = false;
        state.matches = action.payload;
      })
      .addCase(fetchMatchesByRoundId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchMatchesByRoundId.fulfilled, (state, action) => {
        state.loading = false;
        state.matches = action.payload;
      });
  },
  reducers: {},
});

export default matchSlice.reducer;
