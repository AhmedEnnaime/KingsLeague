import { createSlice } from "@reduxjs/toolkit";
import IMatchDay from "../../interfaces/IMatchDay";
import { fetchMatchDaysByTournamentId } from "./matchDayActions";

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
      });
  },
  reducers: {},
});

export default matchDaySlice.reducer;
