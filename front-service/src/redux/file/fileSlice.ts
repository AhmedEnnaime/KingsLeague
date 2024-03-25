import { createSlice } from "@reduxjs/toolkit";
import { uploadImage } from "./fileActions";

interface fileState {
  file: string | null;
  loading: boolean;
}

const initialState: fileState = {
  file: null,
  loading: false,
};

const fileSlice = createSlice({
  name: "file",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(uploadImage.pending, (state) => {
        state.loading = true;
      })
      .addCase(uploadImage.fulfilled, (state, action) => {
        state.loading = false;
        state.file = action.payload.url;
      });
  },
  reducers: {},
});

export default fileSlice.reducer;
