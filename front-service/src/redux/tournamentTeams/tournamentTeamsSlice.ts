import { createSlice } from "@reduxjs/toolkit";
import ITournamentTeams from "../../interfaces/ITournamentTeams";
import { fetchTournamentTeamsByTournamentId } from "./tournamentTeamsActions";
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
      .addCase(fetchTournamentTeamsByTournamentId.pending, (state) => {
        state.loading = true;
      })
      .addCase(
        fetchTournamentTeamsByTournamentId.fulfilled,
        (state, action) => {
          state.loading = false;
          state.teams = action.payload;
        }
      );
  },
  reducers: {},
});

export default tournamentTeamSlice.reducer;
