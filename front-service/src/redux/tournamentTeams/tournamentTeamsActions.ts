import { createAsyncThunk } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import API from "../../utils/API";
import ITournamentTeams from "../../interfaces/ITournamentTeams";
import { AxiosResponse } from "axios";
import { TournamentTeamKey } from "../../embddables/TournamentTeamKey";

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

export const removeTeamFromTournament = createAsyncThunk<
  Map<string, string>,
  TournamentTeamKey
>("tournamentTeam/remove", async (id) => {
  const response: AxiosResponse = await API.delete(
    `/TOURNAMENTTEAMS-SERVICE/api/v1/tournamentTeams/${id.teamId}/tournament/${id.tournamentId}`
  );
  return response.data;
});
