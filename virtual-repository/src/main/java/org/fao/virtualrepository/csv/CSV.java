package org.fao.virtualrepository.csv;

import static org.fao.virtualrepository.Utils.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.fao.virtualrepository.AssetType;
import org.fao.virtualrepository.Property;
import org.fao.virtualrepository.impl.AbstractAsset;
import org.fao.virtualrepository.spi.RepositoryService;

/**
 * An 'Asset' available in the CSV standard.
 * 
 * @author Fabio Simeoni
 * 
 */
public class CSV extends AbstractAsset {

	// constants

	public static final String name = "text/csv";
	public static final String delimiter = "delimiter";
	public static final String quote = "quote";
	public static final String header = "header";
	public static final String encoding = "encoding";
	public static final String columns = "columns";
	
	public static final char defaultDelimiter = ',';
	public static final char defaultQuote = '"';
	public static final Charset defaultEncoding = Charset.forName("UTF-8");
	public static final boolean defaultHeader = false;
	
	

	/**
	 * The type of CSV assets.
	 */
	public static AssetType<CSV> type = new AssetType<CSV>() {

		public QName name() {
			return new QName(name);
		};

		public String toString() {
			return name;
		};
	};

	/**
	 * Creates an instance with a given identifier, name, and repository.
	 * 
	 * @param id the identifier
	 * @param name the name
	 * @param repository the repository
	 * @param properties the properties
	 */
	public CSV(String id, String name, RepositoryService repository) {

		super(id, name, repository, defaultProperties());

	}

	// helper
	private static Property<?>[] defaultProperties() {

		return new Property[] { 
				delimiter(defaultDelimiter), quote(defaultQuote), header(defaultHeader), encoding(defaultEncoding)
		};
	}

	@Override
	public AssetType<CSV> type() {
		return type;
	}

	/**
	 * Returns the delimiter character used in the content of this asset (by default a comma, <code>\u002C</code>).
	 * 
	 * @return the delimiter character
	 */
	public char delimiter() {
		return properties().lookup(delimiter, Character.class).value();
	}

	/**
	 * Sets the delimiter character used in the content of this asset, overwriting the default (a comma, <code>\u002C</code>).
	 * 
	 * @param delimiter the delimiter character
	 */
	public void setDelimiter(char delimiter) {
		properties().add(delimiter(delimiter));
	}
	
	public static void main(String[] args) {
		System.out.println('\u002C');
	}

	/**
	 * Returns the quote character used in the content of this asset (by default a quotation mark, <code>\u0022</code>).
	 * 
	 * @return the quote character
	 */
	public char quote() {
		return properties().lookup(quote, Character.class).value();
	}

	/**
	 * Sets the quote character used in the content of this asset, overriding the default (a quotation mark, <code>\u0022</code>).
	 * 
	 * @param quote the quote character
	 */
	public void setQuote(char quote) {
		properties().add(quote(quote));
	}

	/**
	 * Indicates whether the content of this asset has a header row (by default <code>false</code>).
	 * @return <code>true</code> if the content of this asset has a header row
	 */
	public boolean hasHeader() {
		return properties().lookup(header, Boolean.class).value();
	}

	/**
	 * Indicates whether the content of this asset has a header row, overriding the default (<code>false</code>).
	 * 
	 * @param header <code>true</code> if the content of this asset has a header row
	 */
	public void setHeader(boolean header) {
		properties().add(header(header));
	}
	
	/**
	 * Returns the encoding of the content of this asset (by default <code>UTF-8</code>).
	 * @return the encoding of the content of this asset
	 */
	public Charset encoding() {
		return properties().lookup(encoding, Charset.class).value();
	}

	/**
	 * Sets the encoding of the content of this asset, overriding the default (<code>UTF-8</code>).
	 * 
	 * @param encoding the encoding
	 */
	public void setEncoding(Charset encoding) {
		notNull("encoding",encoding);
		properties().add(encoding(encoding));
	}
	
	/**
	 * Returns the columns of this asset.
	 * @return the columns
	 */
	@SuppressWarnings("unchecked")
	public List<Column> columns() {
		
		return (List<Column>) properties().lookup(columns, List.class).value();

	}

	/**
	 * Sets the columns of this asset.
	 * 
	 * @param cols the columns
	 */
	public void setColumns(Column ... cols) {
		
		notNull("columns",cols);
		
		properties().add(columns(cols));
	}
	

	// helpers

	private static Property<Character> delimiter(char d) {
		return new Property<Character>(delimiter, d, "column delimiter character");
	}

	private static Property<Character> quote(char q) {
		return new Property<Character>(quote, q, "value quote character");
	}

	private static Property<Boolean> header(boolean h) {
		return new Property<Boolean>(header, h, "flags existence of a header row");
	}
	
	private static Property<Charset> encoding(Charset c) {
		return new Property<Charset>(encoding, c, "charset for content encoding");
	}
	
	private static Property<List<Column>> columns(Column ... cols) {
		return new Property<List<Column>>(columns,Arrays.asList(cols), "columns");
	}

}
