import { createSlice } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import {
  createTeam,
  deleteTeam,
  fetchAllTeams,
  fetchSelectedTeam,
  updateTeam,
} from "./teamActions";

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
      })
      .addCase(createTeam.pending, (state) => {
        state.loading = true;
      })
      .addCase(createTeam.fulfilled, (state, action) => {
        state.loading = false;
        state.teams.push(action.payload);
      })
      .addCase(deleteTeam.pending, (state, action) => {
        state.loading = true;
        state.teams = state.teams.filter((team) => team.id !== action.meta.arg);
      })
      .addCase(updateTeam.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateTeam.fulfilled, (state, action) => {
        state.loading = false;
        const updatedTeamId = action.meta.arg.id;
        const updatedTeam = action.payload;
        const existingTeam = state.teams.find(
          (team) => team.id === updatedTeamId
        );
        if (existingTeam) {
          Object.assign(existingTeam, updatedTeam);
        }
      });
  },
  reducers: {},
});

export default teamSlice.reducer;
