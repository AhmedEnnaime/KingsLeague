import { createSlice } from "@reduxjs/toolkit";
import IMatchDay from "../../interfaces/IMatchDay";
import {
  createMatchDay,
  fetchMatchDaysByTournamentId,
} from "./matchDayActions";

interface MatchDayState {
  matchDays: IMatchDay[];
  loading: boolean;
}

const initialState: MatchDayState = {
  matchDays: [],
  loading: false,
};

const matchDaySlice = createSlice({
  name: "matchDay",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchMatchDaysByTournamentId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchMatchDaysByTournamentId.fulfilled, (state, action) => {
        state.loading = false;
        state.matchDays = action.payload;
      })
      .addCase(createMatchDay.pending, (state) => {
        state.loading = true;
      })
      .addCase(createMatchDay.fulfilled, (state, action) => {
        state.loading = false;
        state.matchDays.push(action.payload);
      });
  },
  reducers: {},
});

export default matchDaySlice.reducer;
