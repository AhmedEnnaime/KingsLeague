import { createSlice } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import { fetchAllTeams, fetchSelectedTeam } from "./teamActions";

interface TeamState {
  teams: ITeam[];
  selectedTeam: ITeam | null;
  loading: boolean;
}

const initialState: TeamState = {
  teams: [],
  selectedTeam: null,
  loading: false,
};

const teamSlice = createSlice({
  name: "team",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllTeams.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllTeams.fulfilled, (state, action) => {
        state.loading = false;
        state.teams = action.payload;
      })
      .addCase(fetchSelectedTeam.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchSelectedTeam.fulfilled, (state, action) => {
        state.loading = false;
        state.selectedTeam = action.payload;
      });
  },
  reducers: {},
});

export default teamSlice.reducer;
