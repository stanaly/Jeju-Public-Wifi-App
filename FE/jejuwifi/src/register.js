import React from "react";
import { Box, Typography, TextField, Button } from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material";
import { useNavigate } from "react-router-dom";

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

export default function Register() {
  const Navigate = useNavigate();
  const [email, setEmail] = React.useState("");
  const [userName, setUserName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [repeatPassword, setRepeatPassword] = React.useState("");

  return (
    <ThemeProvider theme={theme}>
      <Box sx={{ height: "100vh" }}>
        <Box sx={{ textAlign: "center", marginTop: "80px" }}>
          <Typography variant="h1">회원가입</Typography>
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
            id="Email"
            label="Email"
            variant="outlined"
            sx={{
              width: "300px",
              height: "70px",
              marginTop: "60px",
              marginBottom: "10px",
            }}
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
          <TextField
            id="UserName"
            label="User Name"
            variant="outlined"
            sx={{
              width: "300px",
              height: "70px",
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
          <TextField
            id="RepeatPassword"
            label="Repeat Password"
            variant="outlined"
            sx={{
              width: "300px",
              height: "70px",
              marginBottom: "30px",
            }}
            value={repeatPassword}
            onChange={(e) => {
              setRepeatPassword(e.target.value);
              if (repeatPassword === password) {
                console.log("비밀번호가 일치합니다.");
              } else {
                console.log("비밀번호가 일치하지 않습니다.");
              }
            }}
          />
          <Button
            sx={{ marginBottom: "50px", width: "300px", height: "70px" }}
            variant="contained"
            onClick={() => {
              if (
                email === "" ||
                userName === "" ||
                password === "" ||
                repeatPassword === ""
              ) {
                alert("빈칸을 모두 채워주세요.");
              } else if (password !== repeatPassword) {
                alert("비밀번호가 일치하지 않습니다.");
              } else {
                alert("회원가입이 완료되었습니다.");
                Navigate("/login");
              }
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
          <Button
            sx={{ marginTop: "30px", width: "150px", height: "70px" }}
            variant="outlined"
            onClick={() => {
              Navigate("/login");
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
        </Box>
      </Box>
    </ThemeProvider>
  );
}
