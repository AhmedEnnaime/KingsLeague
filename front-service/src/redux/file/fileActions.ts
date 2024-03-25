import { createAsyncThunk } from "@reduxjs/toolkit";
import uploadApi from "../../utils/uploadAPI";

export const uploadImage = createAsyncThunk<
  { url: string; done: boolean },
  FormData
>("file/upload-image", async (file) => {
  try {
    const { data } = await uploadApi.post("/upload-image", file);
    return { done: true, url: data.url };
  } catch (error) {
    return { done: false, url: "" };
  }
});
