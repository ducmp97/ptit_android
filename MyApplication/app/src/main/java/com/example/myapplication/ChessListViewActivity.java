package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChessListViewActivity extends AppCompatActivity {

    ListView lvChessList;

    ChessAdapter chessAdapter;

    List<Chess> listChess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_list_view);
        lvChessList = findViewById(R.id.lvChessList);

        listChess = new ArrayList<>();

        String[] name = getResources().getStringArray(R.array.pieces);
        String[] description = getResources().getStringArray(R.array.descriptions);


        for (int i=0; i< name.length; i++){
            String split[] = name[i].split("-");
            String image = split[1].trim().toLowerCase();
            listChess.add(new Chess(name[i], getResources().getIdentifier(image, "drawable", getPackageName()), description[i]));
        }
//        listChess.add(new Chess("King", R.drawable.king, "The king moves exactly one vacant square in any direction: forwards, backwards, left, right, or diagonally. It can also castle in conjunction with a rook."));
//        listChess.add(new Chess("Queen", R.drawable.queen, "The queen moves any number of vacant squares in any direction: forwards, backwards, left, right, or diagonally, in a straight line."));
//        listChess.add(new Chess("Bishop", R.drawable.bishop, "The bishop moves any number of vacant squares diagonally in a straight line. Consequently, a bishop stays on squares of the same color throughout a game. The two bishops each player starts with move on squares of opposite colors."));
//        listChess.add(new Chess("Knight", R.drawable.knight, "The knight moves on an extended diagonal from one corner of any 2×3 rectangle of squares to the furthest opposite corner. Consequently, the knight alternates its square color each time it moves. The knight is the only piece that jumps over any intervening piece(s) when moving (castling being the only special instance in which pieces jump over one another)."));
//        listChess.add(new Chess("Rook", R.drawable.rook, "The rook moves any number of vacant squares forwards, backwards, left, or right in a straight line. It also takes part, along with the king, in a special move called castling"));
//        listChess.add(new Chess("Pawn", R.drawable.pawn, "The pawn moves forward exactly one space, or optionally, two spaces when on its starting square, toward the opponent\\'s side of the board. When there is an enemy piece one square diagonally ahead of the pawn, either left or right, then the pawn may capture that piece. A pawn can perform a special type of capture of an enemy pawn called en passant. If the pawn reaches a square on the back rank of the opponent, it promotes to the player\\'s choice of a queen, rook, bishop, or knight"));

        chessAdapter = new ChessAdapter(this, R.layout.chess_list_item, listChess);

        lvChessList.setAdapter(chessAdapter);

        lvChessList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChessListViewActivity.this, ChessDetailActivity.class);
                intent.putExtra("image", listChess.get(position).getImage());
                intent.putExtra("description", listChess.get(position). getDescription());

                startActivity(intent);
            }
        });
    }
}
