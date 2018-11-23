package com.bc.trial1;

import java.util.ArrayList;
import java.util.Date;

import com.bc.trial1.Transaction.Transaction;
import com.bc.trial1.util.StringUtil;

public class Block {

	private String hash;
	private String previousHash;
	private long timeStamp;
	private String data;
	private int nonce;
	// our data will be a simple message.
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	public String merkleRoot;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public Block(String data, String previousHash) {
		super();
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();

		this.hash = calculateHash();
	}

	// Block Constructor.
	public Block(String previousHash) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();

		this.hash = calculateHash(); // Making sure we do this after we set the other values.
	}

	public String calculateHash() {
		String calculatedhash = StringUtil
				.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot);
		return calculatedhash;
	}

	/**
	 * We will require miners to do proof-of-work by trying different variable
	 * values in the block until its hash starts with a certain number of 0’s.
	 * 
	 * @param difficulty
	 */
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
		while (!hash.substring(0, difficulty).equals(target)) {

			// System.out.println(" iteration " + nonce + " - " + hash);
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

	/** Add transactions to this block */
	public boolean addTransaction(Transaction transaction) {
		// process transaction and check if valid, unless block is genesis block then
		// ignore.
		if (transaction == null)
			return false;
		if ((previousHash != "0")) {
			if ((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
}
