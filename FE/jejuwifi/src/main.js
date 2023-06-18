import { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import RestoreIcon from "@mui/icons-material/Restore";
import FavoriteIcon from "@mui/icons-material/Favorite";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { ThemeProvider, Typography, createTheme } from "@mui/material";
import axios from "axios";

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

export default function Main() {
  const [value, setValue] = useState(0);
  const [data, setData] = useState([{ a: { b: "c" } }]);

  useEffect(() => {
    axios
      .get(
        `https://open.jejudatahub.net/api/proxy/Dtb18ta1btbD1Da1a81aaDttab6tDabb/${process.env.REACT_APP_JEJUPUBLICWIFI}?baseDate=20230608&apGroupName=&category=&addressDong=&addressDetail=&limit=1`
      )
      .then((res) => {
        console.log(res);
        console.log(res.data.data);
        setData(res.data.data);
      });
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <Box
        sx={{
          height: "97vh",
          display: "flex",
          flexDirection: "column",
          justifyContent: "end",
        }}
      >
        <Typography>
          {data.map((item) => (
            <div>
              {item.map((item2) => (
                <div>{item2}</div>
              ))}
            </div>
          ))}
        </Typography>

        <BottomNavigation
          sx={{}}
          showLabels
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
        >
          <BottomNavigationAction label="홈" icon={<RestoreIcon />} />
          <BottomNavigationAction label="검색" icon={<FavoriteIcon />} />
          <BottomNavigationAction label="즐겨찾기" icon={<LocationOnIcon />} />
          <BottomNavigationAction label="설정" icon={<LocationOnIcon />} />
        </BottomNavigation>
      </Box>
    </ThemeProvider>
  );
}
