import { createSlice } from "@reduxjs/toolkit";
import ITournamentTeams from "../../interfaces/ITournamentTeams";
import {
  fetchAllTournamentTeams,
  fetchTournamentTeamsByTournamentId,
  registerTeamInTournament,
  removeTeamFromTournament,
} from "./tournamentTeamsActions";
import ITeam from "../../interfaces/ITeam";
import ITournament from "../../interfaces/ITournament";

interface TournamentTeamState {
  tournamentTeams: ITournamentTeams[];
  teams: ITeam[];
  status: number;
  tournaments: ITournament[];
  loading: boolean;
}

const initialState: TournamentTeamState = {
  tournamentTeams: [],
  teams: [],
  status: 200,
  tournaments: [],
  loading: false,
};

const tournamentTeamSlice = createSlice({
  name: "tournamentTeam",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllTournamentTeams.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllTournamentTeams.fulfilled, (state, action) => {
        state.loading = false;
        state.status = 200;
        state.tournamentTeams = action.payload;
      })
      .addCase(fetchTournamentTeamsByTournamentId.pending, (state) => {
        state.loading = true;
      })
      .addCase(
        fetchTournamentTeamsByTournamentId.fulfilled,
        (state, action) => {
          state.loading = false;
          state.teams = action.payload;
        }
      )
      .addCase(registerTeamInTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(registerTeamInTournament.rejected, (state) => {
        state.loading = true;
        state.status = 500;
      })
      .addCase(registerTeamInTournament.fulfilled, (state, action) => {
        state.tournamentTeams.push(action.payload);
        state.status = 201;
      })
      .addCase(removeTeamFromTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(removeTeamFromTournament.fulfilled, (state, action) => {
        state.loading = false;
        state.tournamentTeams = state.tournamentTeams.filter(
          (tournamentTeam) => tournamentTeam.id !== action.meta.arg
        );
      });
  },
  reducers: {},
});

export default tournamentTeamSlice.reducer;
