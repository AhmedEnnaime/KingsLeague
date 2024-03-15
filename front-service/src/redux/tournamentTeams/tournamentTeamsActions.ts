import { createAsyncThunk } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import API from "../../utils/API";
import ITournamentTeams from "../../interfaces/ITournamentTeams";

export const fetchAllTournamentTeams = createAsyncThunk<ITournamentTeams[]>(
  "tournamentTeam/all",
  async () => {
    const { data } = await API.get(
      `/TOURNAMENTTEAMS-SERVICE/api/v1/tournamentTeams`
    );
    return data;
  }
);

export const fetchTournamentTeamsByTournamentId = createAsyncThunk<
  ITeam[],
  number
>("tournament/teams", async (tournamentId) => {
  const { data } = await API.get(
    `/TOURNAMENTTEAMS-SERVICE/api/v1/tournamentTeams/teams/${tournamentId}`
  );
  return data;
});
