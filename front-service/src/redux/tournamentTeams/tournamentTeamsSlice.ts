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
  status: number | null;
  tournaments: ITournament[];
  loading: boolean;
}

const initialState: TournamentTeamState = {
  tournamentTeams: [],
  teams: [],
  status: null,
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
      .addCase(registerTeamInTournament.pending, (state) => {
        state.loading = true;
      })
      .addCase(registerTeamInTournament.rejected, (state) => {
        state.loading = true;
        state.status = 500;
      })
      .addCase(registerTeamInTournament.fulfilled, (state, action) => {
        state.status = 201;
        state.tournamentTeams.push(action.payload);
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
