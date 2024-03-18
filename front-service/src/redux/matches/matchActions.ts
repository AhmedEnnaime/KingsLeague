import { createAsyncThunk } from "@reduxjs/toolkit";

import API from "../../utils/API";
import { AxiosResponse } from "axios";
import IMatch from "../../interfaces/IMatch";

export const fetchAllMatches = createAsyncThunk<IMatch[]>(
  "match/all",
  async () => {
    const { data } = await API.get(`/MATCH-SERVICE/api/v1/matches`);
    return data;
  }
);

export const createMatch = createAsyncThunk<IMatch, Partial<IMatch>>(
  "match/create",
  async (match) => {
    const { data } = await API.post(`/MATCH-SERVICE/api/v1/matches`, match);
    return data;
  }
);

export const deleteMatch = createAsyncThunk<Map<string, string>, number>(
  "match/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/MATCH-SERVICE/api/v1/matches/${id}`
    );
    return response.data;
  }
);

export const updateMatch = createAsyncThunk<
  IMatch,
  { id: number; matchData: Partial<IMatch> }
>("match/update", async ({ id, matchData }) => {
  const { data } = await API.put(
    `/MATCH-SERVICE/api/v1/matches/${id}`,
    matchData
  );
  return data;
});

export const fetchMatchesByMatchDayId = createAsyncThunk<IMatch[], number>(
  "match/matchDay",
  async (matchDayId) => {
    const { data } = await API.get(
      `/MATCH-SERVICE/api/v1/matches/matchDay/${matchDayId}`
    );
    return data;
  }
);

export const fetchMatchesByRoundId = createAsyncThunk<IMatch[], number>(
  "match/matchDay",
  async (matchDayId) => {
    const { data } = await API.get(
      `/MATCH-SERVICE/api/v1/matches/matchDay/${matchDayId}`
    );
    return data;
  }
);
