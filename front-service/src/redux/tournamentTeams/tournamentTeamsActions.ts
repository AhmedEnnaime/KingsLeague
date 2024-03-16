import { createAsyncThunk } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import API from "../../utils/API";
import ITournamentTeams from "../../interfaces/ITournamentTeams";
import { AxiosResponse } from "axios";
import { TournamentTeamKey } from "../../embddables/TournamentTeamKey";
import ITournament from "../../interfaces/ITournament";

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

export const registerTeamInTournament = createAsyncThunk<
  ITournamentTeams,
  Partial<ITournamentTeams>
>("tournamentTeam/register", async (tournamentTeam) => {
  const { data } = await API.post(
    `/TOURNAMENTTEAMS-SERVICE/api/v1/tournamentTeams`,
    tournamentTeam
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

export const fetchTournamentTeamsByTeamId = createAsyncThunk<
  ITournament[],
  number
>("tournament/tournaments", async (teamId) => {
  const { data } = await API.get(
    `/TOURNAMENTTEAMS-SERVICE/api/v1/tournamentTeams/tournaments/${teamId}`
  );
  return data;
});
