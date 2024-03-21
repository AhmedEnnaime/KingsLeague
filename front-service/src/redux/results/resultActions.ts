import { createAsyncThunk } from "@reduxjs/toolkit";
import IResult from "../../interfaces/IResult";
import API from "../../utils/API";

export const createResult = createAsyncThunk<IResult, Partial<IResult>>(
  "result/create",
  async (result) => {
    const { data } = await API.post(`/MATCH-SERVICE/api/v1/results`, result);
    return data;
  }
);

export const fetchResultByMatchId = createAsyncThunk<IResult, number>(
  "result/match",
  async (matchId) => {
    const { data } = await API.get(
      `/MATCH-SERVICE/api/v1/results/match/${matchId}`
    );
    return data;
  }
);
