import React from "react";
import { Box, Typography, TextField, Button, ButtonBase } from "@mui/material";
import Image from "mui-image";
import google from "./asset/google.png";
import kakao from "./asset/kakao.png";
import naver from "./asset/naver.png";
import { ThemeProvider, createTheme } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

const theme = createTheme({
  typography: {
    fontFamily: "'Apple SD Gothic Neo', serif",
  },
  palette: {
    primary: {
      main: "#000000",
    },
  },
});

export default function Login() {
  const Navigate = useNavigate();
  const [userName, setUserName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [cookies, setCookie] = useCookies(["jwt"]);

  return (
    <ThemeProvider theme={theme}>
      <Box sx={{ height: "100vh" }}>
        <Box sx={{ textAlign: "center", marginTop: "80px" }}>
          <Typography variant="h1">로그인</Typography>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <TextField
            id="UserName"
            label="User Name"
            variant="outlined"
            sx={{
              width: "300px",
              height: "70px",
              marginTop: "60px",
              marginBottom: "10px",
            }}
            value={userName}
            onChange={(e) => {
              setUserName(e.target.value);
            }}
          />
          <TextField
            id="Password"
            label="Password"
            variant="outlined"
            sx={{
              width: "300px",
              height: "70px",
              marginBottom: "10px",
            }}
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
          <Button
            sx={{ marginBottom: "50px", width: "300px", height: "70px" }}
            variant="contained"
            onClick={() => {
              Navigate("/main");
            }}
          >
            <Typography
              sx={{
                fontSize: "25px",
              }}
            >
              로그인
            </Typography>
          </Button>
          <ButtonBase sx={{ marginBottom: "5px" }}>
            <Image
              src={google}
              alt="google"
              duration={0}
              height={70}
              width={310}
            />
          </ButtonBase>
          <ButtonBase sx={{ marginBottom: "5px" }}>
            <Image
              src={kakao}
              alt="google"
              duration={0}
              height={70}
              width={300}
            />
          </ButtonBase>
          <ButtonBase sx={{ marginBottom: "5px" }}>
            <Image
              src={naver}
              alt="google"
              duration={0}
              height={70}
              width={300}
            />
          </ButtonBase>
          <Button
            sx={{ marginTop: "30px", width: "150px", height: "70px" }}
            variant="outlined"
            onClick={() => {
              Navigate("/register");
            }}
          >
            <Typography
              sx={{
                fontSize: "25px",
              }}
            >
              회원가입
            </Typography>
          </Button>
        </Box>
      </Box>
    </ThemeProvider>
  );
}
