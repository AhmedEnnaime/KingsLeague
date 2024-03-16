import { createSlice } from "@reduxjs/toolkit";
import ITournamentTeams from "../../interfaces/ITournamentTeams";
import {
  fetchAllTournamentTeams,
  fetchTournamentTeamsByTeamId,
  fetchTournamentTeamsByTournamentId,
  registerTeamInTournament,
  removeTeamFromTournament,
} from "./tournamentTeamsActions";
import ITeam from "../../interfaces/ITeam";
import ITournament from "../../interfaces/ITournament";

interface TournamentTeamState {
  tournamentTeams: ITournamentTeams[];
  teams: ITeam[];
  tournaments: ITournament[];
  loading: boolean;
}

const initialState: TournamentTeamState = {
  tournamentTeams: [],
  teams: [],
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
      .addCase(fetchTournamentTeamsByTeamId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTournamentTeamsByTeamId.fulfilled, (state, action) => {
        state.loading = false;
        state.tournaments = action.payload;
      })
      .addCase(registerTeamInTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(registerTeamInTournament.rejected, (state) => {
        state.loading = true;
      })
      .addCase(registerTeamInTournament.fulfilled, (state, action) => {
        state.tournamentTeams.push(action.payload);
      })
      .addCase(removeTeamFromTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(removeTeamFromTournament.fulfilled, (state, action) => {
        state.loading = false;
        state.tournamentTeams = state.tournamentTeams.filter(
          (tournamentTeam) =>
            JSON.stringify(tournamentTeam.id) !==
            JSON.stringify(action.meta.arg)
        );
      });
  },
  reducers: {},
});

export default tournamentTeamSlice.reducer;
