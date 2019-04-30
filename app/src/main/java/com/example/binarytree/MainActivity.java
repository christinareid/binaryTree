package com.example.binarytree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView payloadTV;
    private Button leftButton, rightButton;
    private BinaryTree2 myTree;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.payloadTV = findViewById(R.id.payloadTV);
        this.leftButton = findViewById(R.id.leftButton);
        this.rightButton = findViewById(R.id.rightButton);

        if(this.getIntent().hasExtra("secretCode"))
        {
            this.myTree = Core.theVault.getTreeWithSuperSecretCode(this.getIntent().getStringExtra("secretCode"));
        }
        else
        {
            this.myTree = new BinaryTree2(5);
            this.myTree.add(3);
            this.myTree.add(3);
            this.myTree.add(8);
            this.myTree.add(6);
            this.myTree.visitInOrder();
            Core.theVault.addTree(Integer.toString(Core.currentCode), this.myTree);
        }

        this.payloadTV.setText("" + this.myTree.payload);
        this.hideButtonsIfNeeded();

    }

    private void hideButtonsIfNeeded()
    {
        if(this.myTree.left == null)
        {
            this.leftButton.setVisibility(View.INVISIBLE);
        }

        if(this.myTree.right == null)
        {
            this.rightButton.setVisibility(View.INVISIBLE);
        }
    }

    public void leftButtonPressed(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        Core.theVault.addTree(Integer.toString(Core.currentCode), this.myTree.left);
        i.putExtra("secretCode", Integer.toString(Core.currentCode++));
        this.startActivity(i);
    }

    public void rightButtonPressed(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        Core.theVault.addTree(Integer.toString(Core.currentCode), this.myTree.right);
        i.putExtra("secretCode", Integer.toString(Core.currentCode++));
        this.startActivity(i);
    }
}
